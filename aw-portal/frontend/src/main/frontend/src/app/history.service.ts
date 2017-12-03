import { Injectable } from "@angular/core";
import { AwHistory } from './domain/aw-history';
import { Observable } from 'rxjs/Observable';
import { BackendService } from './backend.service';


@Injectable()
export class HistoryService {
    activity: AwHistory = new AwHistory();
    constructor(private backend: BackendService) {}

    createHistory(action: string): Observable<AwHistory> {
        this.activity.boardId = parseInt(localStorage.getItem("currentBoardId"));
        this.activity.userId = parseInt(localStorage.getItem("currentUserId"));
        this.activity.action = localStorage.getItem("userUsername") + action;
        this.activity.timestamp = Date.now();
        return this.backend.post("/aw_history/createHistory", this.activity);
    }

    getHistory(boardId: number): Observable<AwHistory[]> {
        return this.backend.get("/getHistoryByBoardId/" + boardId);
    }

    
}