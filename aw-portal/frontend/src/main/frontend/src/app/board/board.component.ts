import { DataService } from './../data.service';
import { Component, OnInit } from '@angular/core';
import { NgModel } from '@angular/forms';
import { Router } from '@angular/router';

export class AwBoard{
    id: number;
    name: string;
    timeCompleted: string;
    duration: number;
}

@Component({
    selector: 'aw-board',
    templateUrl: './board.component.html',
    styleUrls: ['./board.component']
})
export class BoardComponent implements OnInit{
    bu: AwBoard = {
        id: 0,
        name: "",
        timeCompleted: "",
        duration: 0
    };
    errorMessage: string = "";

    constructor(private service: DataService,
    private router: Router){ }
    
    ngOnInit(){ }

    display: boolean = false;

    toggleDisplay(event){
        this.display = !this.display;
    }
    deleteBoard(board: AwBoard){

    }
    editBoard(name:string){

    }
};