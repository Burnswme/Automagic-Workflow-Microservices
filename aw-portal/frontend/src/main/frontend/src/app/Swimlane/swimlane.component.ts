import { AwStory } from './../domain/aw-story';
import { StoryService } from './../story/story.service';
import { AwSwimlane } from './../domain/aw-swimlane';
import { AwBoard } from './../domain/aw-board';
import { Router, ActivatedRoute } from '@angular/router';
import { SwimlaneService } from './swimlane.service';
import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'aw-swimlane',
  templateUrl: './swimlane.component.html',
  styleUrls: ['./swimlane.component.css']
})
export class SwimlaneComponent implements OnInit {
  @Input() board: AwBoard;
  @Input() swimlane: AwSwimlane;
  sl2: AwSwimlane; //swimlane to be displaced when moving a swimlane around
  swapSl: AwSwimlane; //for swapping

  constructor(private sls: SwimlaneService,
              private sts: StoryService,
              private route: ActivatedRoute,
              private router: Router) {}

  ngOnInit() {
    this.sts.getStories(this.swimlane.id).subscribe((stories: AwStory[]) => {
      this.swimlane.stories = stories;
    });
  }

  updateSwimlane(updatedName: string) {
    console.log("UPDATE SWIMLANE")
    console.log(updatedName);
    this.swimlane.name = updatedName;
    this.board.swimlanes[this.swimlane.order] = this.swimlane;
    this.sls.updateSwimlane(this.swimlane).subscribe();
  }

  moveLeft() {
    console.log("MOVE LEFT");
    var ogOrder = this.swimlane.order;
    this.swimlane.order = ogOrder-1;
    this.sl2 = this.board.swimlanes[this.swimlane.order];
    this.sl2.order = ogOrder;
    this.swapSl = this.sl2;
    this.board.swimlanes[ogOrder] = this.swapSl;
    this.board.swimlanes[ogOrder-1] = this.swimlane;
    console.log(this.sl2);
    console.log(this.swimlane);
    console.log(this.board.swimlanes);
    this.sls.updateSwimlane(this.swimlane).subscribe();
    this.sls.updateSwimlane(this.sl2).subscribe();
  }

  moveRight() {
    console.log("MOVE RIGHT");
    var ogOrder = this.swimlane.order;
    this.swimlane.order = ogOrder+1;
    this.sl2 = this.board.swimlanes[this.swimlane.order];
    this.sl2.order = ogOrder;
    this.swapSl = this.sl2;
    this.board.swimlanes[ogOrder] = this.swapSl;
    this.board.swimlanes[ogOrder+1] = this.swimlane;
    console.log(this.sl2);
    console.log(this.swimlane);
    console.log(this.board.swimlanes);
    this.sls.updateSwimlane(this.swimlane).subscribe();
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

    });
  }

  createStory(title: string, slId: number, desc: string, points: number) {
    console.log("CREATE STORY");
    // sl.order = (this.bu.swimlanes) ? this.bu.swimlanes.length : 0;
    // console.log(sl);
    // this.sls.createSwimlane(sl).subscribe((result: AwSwimlane) => {
    //     this.bu.swimlanes.push(result);
    //     this.sl = {
    //         id: 0,
    //         boardId: this.bu.id,
    //         name: "",
    //         order: 0,
    //         stories: []
    //     };
    // });
}

}
