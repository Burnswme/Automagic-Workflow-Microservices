import { AwTask } from "./aw-task";

export class AwStory {
    id: number;
    swimlaneId: number;
    title: string;
    description: string;
    points: number;
    timeCompleted: number;
    position: number;
    tasks: AwTask[];
}