import { DataService } from './../data.service';
import { Component, OnInit } from '@angular/core';
import { NgModel } from '@angular/forms';
import { Router } from '@angular/router';

export class AwStory{
    id: number;
    swimlaneId: number;
    title: string;
    description: string;
    points: number;
    timeCompleted: string;
    position: number;
}

@Component({
    selector:
    templateUrl:
    styleUrls:
})