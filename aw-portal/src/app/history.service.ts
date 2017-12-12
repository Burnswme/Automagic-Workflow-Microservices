import { Injectable } from "@angular/core";
import { AwHistory } from './domain/aw-history';
import { Observable } from 'rxjs/Observable';
import { BackendService } from './backend.service';
import { environment } from "../environments/environment";


@Injectable()
export class HistoryService {
    historyPath: string = environment.historyPath;
    activity: AwHistory = new AwHistory();
    constructor(private backend: BackendService) {}

    createHistory(action: string): Observable<AwHistory> {
        this.activity.boardId = parseInt(localStorage.getItem("currentBoardId"));
        this.activity.userId = parseInt(localStorage.getItem("currentUserId"));

        this.activity.action = localStorage.getItem("currentUserUsername") + action;
        this.activity.timestamp = new Date();

        return this.backend.post(this.historyPath + "/createHistory", this.activity);
    }

    getHistory(boardId: number): Observable<AwHistory[]> {
        return this.backend.get(this.historyPath + "/getHistory/" + boardId);
    }

    
}