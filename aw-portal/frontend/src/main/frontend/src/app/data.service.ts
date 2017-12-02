import { DatePipe } from '@angular/common';
import { AwStory } from './domain/aw-story';
import { AwBoard } from './domain/aw-board';
import { Injectable } from '@angular/core';

class Point {
  t: Date;
  y: number;
}

@Injectable()
export class DataService {

  constructor(private pipe: DatePipe) {}

  roundDate(timeStamp: number): Date {
    timeStamp -= timeStamp % (24 * 60 * 60 * 1000);//subtract amount of time since midnight
    timeStamp += new Date().getTimezoneOffset() * 60 * 1000;//add on the timezone offset
    return new Date(timeStamp);
  }

  getDataSet(board: AwBoard): Point[] {
    let start = new Date(board.startDate);
    let days = board.numDays;
    let data: Point[] = [];
    for (let i = 0; i <= days; i++) {
      let currentDay = this.roundDate(start.getTime() + i*(1000 * 60 * 60 * 24));
      data.push({t: currentDay, y: 0});
    }
    board.swimlanes.forEach(swimlane => {
      swimlane.stories.forEach(story => {
        let temp = this.getStoryPointBurndown(story, start, days);
        for (let i = 0; i <= days; i++) {
          data[i].y += temp[i];
        }
      });
    });
    return data;
  }

  getStoryPointBurndown(story: AwStory, start: Date, days: number): number[] {
    let out: number[] = [];
    for (let i = 0; i <= days; i++) {
      out.push(
        (story.timeCompleted &&
          new Date(story.timeCompleted) < this.roundDate(start.getTime() + (i+1)*(1000 * 60 * 60 * 24)))
        ? 0 : story.points);
    }
    return out;
  }

  getLabelSet(board: AwBoard): string[] {
    let start = new Date(board.startDate);
    let days = board.numDays;
    let labels: string[] = [];
    for (let i = 0; i <= days; i++) {
      let currentDay = new Date(start.getTime() + i*(1000 * 60 * 60 * 24));
      labels.push(this.pipe.transform(currentDay, 'mediumDate'));
    }
    return labels;
  }



}
