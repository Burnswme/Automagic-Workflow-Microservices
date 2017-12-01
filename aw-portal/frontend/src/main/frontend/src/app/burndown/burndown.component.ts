import { AwBoard } from './../domain/aw-board';
import { Component, OnInit, Input } from '@angular/core';
import { DataService } from '../data.service';

@Component({
  selector: 'aw-burndown',
  templateUrl: './burndown.component.html',
  styleUrls: ['./burndown.component.css']
})
export class BurndownComponent implements OnInit {
  @Input() board: AwBoard;
  min: Date = this.service.roundDate(0);

  constructor(private service: DataService) {}

  ngOnInit() {}
  
  // lineChart
  public lineChartData:Array<any> = [
    {data: [], label: ''}
  ];
  public lineChartLabels:Array<any> = [];
  public lineChartOptions:any = {
    responsive: true,
    scales: {
      xAxes: [{
        type: 'time',
        time: {
          // min: this.min,
          unit: 'day',
          unitStepSize: 1,
          displayFormats: {
            'day': 'MMM DD YY'
          }
        }
      }],
      yAxes: [{
        ticks: {
            beginAtZero: true
        }
      }]
    }
  };
  public lineChartColors:Array<any> = [
    {
      backgroundColor: 'rgba(148,159,177,0.2)',
      borderColor: 'rgba(148,159,177,1)',
      pointBackgroundColor: 'rgba(148,159,177,1)',
      pointBorderColor: '#fff',
      pointHoverBackgroundColor: '#fff',
      pointHoverBorderColor: 'rgba(148,159,177,0.8)',
      lineTension: 0
    }
  ];
  public lineChartLegend:boolean = false;
  public lineChartType:string = 'line';

  public randomize():void {
    let _lineChartData:Array<any> = new Array(this.lineChartData.length);
    for (let i = 0; i < this.lineChartData.length; i++) {
      _lineChartData[i] = {data: new Array(this.lineChartData[i].data.length), label: this.lineChartData[i].label};
      for (let j = 0; j < this.lineChartData[i].data.length; j++) {
        _lineChartData[i].data[j] = Math.floor((Math.random() * 100) + 1);
      }
    }
    this.lineChartData = _lineChartData;
  }

  // events
  public chartClicked(e:any):void {
    console.log(e);
  }

  public chartHovered(e:any):void {
    console.log(e);
  }

  updateData(): void {
    let dataset = this.service.getDataSet(this.board);
    this.lineChartData = [
      {data: dataset, label: 'Data'}
    ];
    this.min = dataset[0].t;
    
  }

}
