import { Observable } from 'rxjs/Observable';
import { BackendService } from './../backend.service';
import { ValidationService } from './../validation.service';
import { AwSwimlane } from './../domain/aw-swimlane';
import { SwimlaneService } from './../swimlane/swimlane.service';
import { AwBoard } from './../domain/aw-board';
import { BoardService } from './board.service';
import { HistoryService } from './../history.service';
import { Component, OnInit } from '@angular/core';
import { NgModel } from '@angular/forms';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';
import 'rxjs/add/operator/switchMap';
import { AwHistory } from '../domain/aw-history';
import { AwUser } from '../domain/aw-user';

@Component({
    selector: 'aw-board',
    templateUrl: './board.component.html',
    styleUrls: ['./board.component.css']
})

export class BoardComponent implements OnInit{
    user: AwUser = new AwUser("", "");
    board: AwBoard = new AwBoard();
    editor: AwBoard = new AwBoard();
    sl: AwSwimlane = new AwSwimlane();
    history: AwHistory[];
    errorMessage: string = "";
    updatedName: string;
    display: boolean = false;
    activity: AwHistory = new AwHistory();

    constructor(private bds: BoardService,
                private sls: SwimlaneService,
                private route: ActivatedRoute,
                private historyService: HistoryService,
                private backend: BackendService,
                private bvs: ValidationService,
                private router: Router) {}
    
    ngOnInit(){
        this.setAwUserListener();
    }
    
    setAwUserListener(): void {
        this.backend.user.subscribe(result => {
            this.user = result;
            if (this.user.username != "")
                this.getBoard();
        });
    }

    getBoard(): void {
        let id;
        this.route.paramMap.switchMap((params: ParamMap) => {
            id = +params.get('id');
            return this.bvs.isAuthorized(this.user, id);
        }).subscribe(result => {
            if (result) {
                this.bds.getBoard(id).subscribe(board => {
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
                    }, (error) => {
                        this.router.navigateByUrl('/boardNotFound');
                    })
                });
            } else {
                this.router.navigateByUrl('/notAuthorized');
            }
        });
    }

    setEdit(): void {
        this.editor = { ...this.board };
    }

    updateBoard(board: AwBoard): void {
        this.bds.updateBoard(board).subscribe(result => {
            this.board = { ...this.editor };
        })
    }

    toggleDisplay(event){
        this.display = !this.display;
    }

    deleteBoard(board: AwBoard){
        this.bds.deleteBoard(board).subscribe(result => {
            if (result) {
                this.bds.removeBoardFromList(board);
                this.router.navigateByUrl('/home');
            }
        })
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
            this.historyService.createHistory(" has created a swimlane with the name " + sl.name).subscribe(hist => {
                //push to history
            });
        });
    }

    loadHistory() {
        this.bds.getHistory(this.board.id).subscribe((histList: AwHistory[]) => {
            this.history = histList;
        })
    }
};