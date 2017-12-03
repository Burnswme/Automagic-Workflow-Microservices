import { AwSwimlane } from './aw-swimlane';
import { AwHistory } from './aw-history';

export class AwBoard {
    id: number;
    name: string;
    startDate: number;
    numDays: number;
    swimlanes: AwSwimlane[];

    history: AwHistory[];

    constructor() {}
}