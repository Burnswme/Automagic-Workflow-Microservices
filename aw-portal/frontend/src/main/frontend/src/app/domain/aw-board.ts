import { AwSwimlane } from './aw-swimlane';

export class AwBoard {
    id: number;
    name: string;
    startDate: number;
    duration: number;
    swimlanes: AwSwimlane[];
}