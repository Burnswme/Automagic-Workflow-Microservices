import { Component, OnInit, Input } from '@angular/core';
import { AwHistory } from '../domain/aw-history';

@Component({
  selector: 'aw-history',
  templateUrl: './history.component.html',
  styleUrls: ['./history.component.css']
})
export class HistoryComponent implements OnInit {
  @Input() hist: AwHistory;
  
  constructor() { }

  ngOnInit() {
  }

}
