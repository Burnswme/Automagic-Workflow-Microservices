export class AwRole {
    userId: number;
    boardId: number;
    roleId: number;
    constructor(userId, boardId, roleId) {
        this.userId = userId;
        this.boardId = boardId;
        this.roleId = roleId;
    }
}