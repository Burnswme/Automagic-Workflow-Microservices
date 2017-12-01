import { AwSwimlane } from './../domain/aw-swimlane';
import { SwimlaneService } from './../swimlane/swimlane.service';
import { AwBoard } from './../domain/aw-board';
import { BoardService } from './board.service';
import { Component, OnInit } from '@angular/core';
import { NgModel } from '@angular/forms';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';
import 'rxjs/add/operator/switchMap';
import { AwHistory } from '../domain/aw-history';

@Component({
    selector: 'aw-board',
    templateUrl: './board.component.html',
    styleUrls: ['./board.component.css']
})
export class BoardComponent implements OnInit{
    board: AwBoard = new AwBoard();
    sl: AwSwimlane = new AwSwimlane();

    history: AwHistory[];

    errorMessage: string = "";

    updatedName: string;

    constructor(private bs: BoardService,
                private sls: SwimlaneService,
                private route: ActivatedRoute,
                private router: Router) {}
    
    ngOnInit(){
        this.route.paramMap
            .switchMap((params: ParamMap) => this.bs.getBoard(+params.get('id')))
            .subscribe(board => {
                this.board = board;
                this.sl.boardId = board.id;
                this.display = true;
                console.log("GETTING SWIMLANES");
                console.log(board.id);
                this.sls.getSwimlanes(board.id)
                .subscribe((swimlanes: AwSwimlane[]) => {
                    board.swimlanes = swimlanes;
                    console.log("SWIMLANES GOTTEN?");
                    console.log(board.swimlanes);
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
        sl.order = (this.board.swimlanes) ? this.board.swimlanes.length : 0;
        console.log(sl);
        this.sls.createSwimlane(sl).subscribe((result: AwSwimlane) => {
            this.board.swimlanes.push(result);
            this.sl = {
                id: 0,
                boardId: this.board.id,
                name: "",
                order: 0,
                stories: []
            };
        });
    }

    loadHistory() {
        this.bs.getHistory(this.board.id).subscribe((histList: AwHistory[]) => {
            this.history = histList;
        })
    }
};