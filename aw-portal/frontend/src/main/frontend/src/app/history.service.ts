import { Injectable } from "@angular/core";
import { AwHistory } from './domain/aw-history';
import { Observable } from 'rxjs/Observable';
import { BackendService } from './backend.service';


@Injectable()
export class HistoryService {
    activity: AwHistory = {
        id: 0,
        userId: 0,
        boardId: 0,
        action: "",
        timestamp: null
    }
    constructor(private backend: BackendService) {}

    createHistory(action: string): Observable<AwHistory> {
        this.activity.boardId = parseInt(localStorage.getItem("currentBoardId"));
        this.activity.userId = parseInt(localStorage.getItem("currentUserId"));
        this.activity.action = localStorage.getItem("currentUserUsername") + action;
        this.activity.timestamp = new Date();
        console.log(this.activity);
        return this.backend.post("/aw_history/createHistory", this.activity);
    }

    getHistory(boardId: number): Observable<AwHistory[]> {
        return this.backend.get("/getHistoryByBoardId/" + boardId);
    }

    
}