import { DataService } from './../data.service';
import { Component, OnInit } from '@angular/core';
import { NgModel } from '@angular/forms';
import { Router } from '@angular/router';

export class AwSwimlane{
    id: number;
    name: string;
    boardId: number;
    position: number;
}

@Component({
    selector: 'aw-swimlane',
    templateUrl: './story.component.html',
    styleUrls: ['./swimlane.compnent.css']
})
export class SwimlaneComponent implements OnInit{
    sw: AwSwimlane = {
        id: 0,
        name: "",
        boardId: 0,
        position: 1
    }
    errorMessage: string = "";

    constructor(private service: DataService,
        private router: Router) { }
    
    ngOnInit(){ }    

    moveLeft(): void{
        if(this.sw.position > 1)
            this.sw.position--;
        else
            this.sw.position = 1;    
    }

    moveRight(): void{
        this.sw.position++;
    }
    editSwimlane(name:string){
        this.sw.name = name;
    }
    deleteSwimlane(sw: AwSwimlane){
        
    }
}