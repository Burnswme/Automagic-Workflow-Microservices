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

  constructor(private sls: SwimlaneService,
              private sts: StoryService,
              private route: ActivatedRoute,
              private router: Router) {}

  ngOnInit() {
    this.sts.getStories(this.swimlane.id).subscribe((stories: AwStory[]) => {
      this.swimlane.stories = stories;
    });
  }

  moveLeft() {}

  moveRight() {}

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

}
