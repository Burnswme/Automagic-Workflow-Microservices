import { AwStory } from './../domain/aw-story';
import { StoryService } from './../story/story.service';
import { AwSwimlane } from './../domain/aw-swimlane';
import { AwBoard } from './../domain/aw-board';
import { Router, ActivatedRoute } from '@angular/router';
import { SwimlaneService } from './swimlane.service';
import { Component, OnInit, Input } from '@angular/core';
import { AwHistory } from './../domain/aw-history';
import { HistoryService } from './../history.service';

@Component({
  selector: 'aw-swimlane',
  templateUrl: './swimlane.component.html',
  styleUrls: ['./swimlane.component.css']
})
export class SwimlaneComponent implements OnInit {
  @Input() board: AwBoard;
  @Input() swimlane: AwSwimlane;
  updateName: string;//used as an intermediary string to update/edit this.swimlane using ngModel without actually saving the value until you hit save

  sl2: AwSwimlane; //swimlane to be displaced when moving a swimlane around
  
  activity: AwHistory = {
    id: 0,
    userId: 0,
    boardId: 0,
    action: "",
    timestamp: null
  };

  newStory: AwStory = {
    id: 0,
    swimlaneId:0,
    title: "",
    description: "",
    points: 0,
    timeCompleted: null,
    order: 0,
    tasks: []
  };

  constructor(private sls: SwimlaneService,
              private sts: StoryService,
              private route: ActivatedRoute,
              private historyService: HistoryService,
              private router: Router) {}

  ngOnInit() {
    this.sts.getStories(this.swimlane.id).subscribe((stories: AwStory[]) => {
      this.swimlane.stories = stories;
    });
    //gives the 'edit' field a default value(the actual value)
    this.updateName = this.swimlane.name;
  }

  updateSwimlane() {
    var oldName = this.swimlane.name;
    this.swimlane.name = this.updateName;
    // this.board.swimlanes[this.swimlane.order] = this.swimlane;
    this.sls.updateSwimlane(this.swimlane).subscribe(sl => {
      this.historyService.createHistory(" has changed a swimlan's name from [" + oldName + "] to [" + sl.name + "]").subscribe(hist => {
        this.board.history.unshift(hist);
      });
    });
  }

  moveLeft() {
    var ogOrder = this.swimlane.order;
    this.swimlane.order = ogOrder-1;
    this.sl2 = this.board.swimlanes[this.swimlane.order];
    this.sl2.order = ogOrder;
    this.board.swimlanes[ogOrder] = this.sl2;
    this.board.swimlanes[ogOrder-1] = this.swimlane;
    this.sls.updateSwimlane(this.swimlane).subscribe(sl => {
      this.historyService.createHistory(" has moved swimlane [" + sl.name + "] to the left "
      + " and [" + this.sl2.name + "] to the right").subscribe(hist => {
        this.board.history.unshift(hist);
      });
    });
    this.sls.updateSwimlane(this.sl2).subscribe();
  }

  moveRight() {
    var ogOrder = this.swimlane.order;
    this.swimlane.order = ogOrder+1;
    this.sl2 = this.board.swimlanes[this.swimlane.order];
    this.sl2.order = ogOrder;
    this.board.swimlanes[ogOrder] = this.sl2;
    this.board.swimlanes[ogOrder+1] = this.swimlane;
    this.sls.updateSwimlane(this.swimlane).subscribe(sl => {
      this.historyService.createHistory(" has moved swimlane [" + sl.name + "] to the right "
      + " and [" + this.sl2.name + "] to the left").subscribe(hist => {
        this.board.history.unshift(hist);
      });
      
    });
    this.sls.updateSwimlane(this.sl2).subscribe();
    
  }

  delete(sl: AwSwimlane): void {
    this.sls.deleteSwimlane(sl).subscribe((b: Boolean) => {
      this.board.swimlanes = this.board.swimlanes.filter(obj => {
				return obj.id !== sl.id;
      });
      
      this.board.swimlanes.forEach(obj => {
        if (obj.order > sl.order) {
          obj.order -= 1;
          this.sls.updateSwimlane(obj).subscribe();
        }
      });
      this.historyService.createHistory(" has deleted swimlane [" + sl.name + "]").subscribe(hist => {
        this.board.history.unshift(hist);
      });
    });
  }

  createStory(st: AwStory) {
    st.order = (this.swimlane.stories) ? this.swimlane.stories.length : 0;
    st.swimlaneId = this.swimlane.id;

    this.sts.createStory(st).subscribe((result: AwStory) => {
      this.swimlane.stories.push(result);
      this.newStory = {
        id: 0,
        swimlaneId:0,
        title: "",
        description: "",
        points: 0,
        timeCompleted: null,
        order: 0,
        tasks: [],
      };

      this.historyService.createHistory(" has created a story with the name [" + st.title + "]").subscribe(hist => {
        this.board.history.unshift(hist);
      })
    })
  }
}
