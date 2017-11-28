import { AwSwimlane } from './../domain/aw-swimlane';
import { SwimlaneService } from './../swimlane/swimlane.service';
import { AwBoard } from './../domain/aw-board';
import { BoardService } from './board.service';
import { Component, OnInit } from '@angular/core';
import { NgModel } from '@angular/forms';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';
import 'rxjs/add/operator/switchMap';

@Component({
    selector: 'aw-board',
    templateUrl: './board.component.html',
    styleUrls: ['./board.component.css']
})
export class BoardComponent implements OnInit{
    bu: AwBoard = {
        id: 0,
        name: "",
        startDate: 0,
        duration: 0,
        swimlanes: []
    };
    sl: AwSwimlane = {
        id: 0,
        boardId: 0,
        name: "",
        order: 0,
        stories: []
    };
    errorMessage: string = "";

    constructor(private bs: BoardService,
                private sls: SwimlaneService,
                private route: ActivatedRoute,
                private router: Router) {}
    
    ngOnInit(){
        this.route.paramMap
            .switchMap((params: ParamMap) => this.bs.getBoard(+params.get('id')))
            .subscribe(board => {
                this.bu = board;
                this.sl.boardId = board.id;
                this.display = true;
                this.sls.getSwimlanes(board.id)
                .subscribe((swimlanes: AwSwimlane[]) => {
                    board.swimlanes = swimlanes;
                })
            });
    };

    display: boolean = false;

    toggleDisplay(event){
        this.display = !this.display;
    }

    deleteBoard(board: AwBoard){

    }

    editBoard(name:string){

    }

    createSwimlane(sl: AwSwimlane) {
        sl.order = (this.bu.swimlanes) ? this.bu.swimlanes.length + 1 : 1;
        console.log(sl);
        this.sls.createSwimlane(sl).subscribe((result: AwSwimlane) => {
            this.bu.swimlanes.push(result);
            this.sl = {
                id: 0,
                boardId: this.bu.id,
                name: "",
                order: 0,
                stories: []
            };
        });
    }
};