import { AwStory } from "./aw-story";

export class AwSwimlane{
    id: number;
    boardId: number;
    name: string;
    order: number;
    stories: AwStory[];
}