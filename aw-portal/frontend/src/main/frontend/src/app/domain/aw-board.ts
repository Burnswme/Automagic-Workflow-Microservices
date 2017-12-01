import { AwSwimlane } from './aw-swimlane';

export class AwBoard {
    id: number;
    name: string;
    startDate: number;
    numDays: number;
    swimlanes: AwSwimlane[];
    constructor() {}
}