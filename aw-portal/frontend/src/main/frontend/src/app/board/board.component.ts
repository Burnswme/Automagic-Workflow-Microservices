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
        swimlanes: null
    };
    errorMessage: string = "";

    constructor(private service: BoardService,
                private route: ActivatedRoute,
                private router: Router) {}
    
    ngOnInit(){ 
        // this.setBoardListener();
        this.route.paramMap
            .switchMap((params: ParamMap) => this.service.loadFullBoard(+params.get('id')))
            .subscribe(board => {
                this.bu = board;
                console.log(board);
                this.display = true;
            });
    };

    display: boolean = false;

    setBoardListener(): void {
        this.service.board.subscribe(board => {
            this.bu = board;
            console.log(board);
            this.display = true;
        });
    }

    toggleDisplay(event){
        this.display = !this.display;
    }

    deleteBoard(board: AwBoard){

    }

    editBoard(name:string){

    }
};