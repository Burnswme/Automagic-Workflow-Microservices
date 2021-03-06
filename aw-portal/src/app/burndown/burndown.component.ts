import { AwBoard } from './../domain/aw-board';
import { Component, Input } from '@angular/core';
import { DataService } from '../data.service';

@Component({
  selector: 'aw-burndown',
  templateUrl: './burndown.component.html',
  styleUrls: ['./burndown.component.css']
})
export class BurndownComponent {
  @Input() board: AwBoard;

  constructor(private service: DataService) {}
  
  // lineChart
  public lineChartData:Array<any> = [{data: []}];
  public lineChartLabels:Array<any> = [];
  public lineChartOptions:any = {
    responsive: true,
    scales: {
      xAxes: [{
        type: 'time',
        time: {
          unit: 'day',
          unitStepSize: 1,
          displayFormats: {
            'day': 'MMM DD YY'
          }
        }
      }],
      yAxes: [{
        scaleLabel: {
          display: true,
          labelString: 'Points to Go',
          fontStyle: 'bold'
        },
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
  }

  public chartHovered(e:any):void {
  }

  updateData(): void {
    this.lineChartData = [{data: this.service.getDataSet(this.board)}];
  }

}
