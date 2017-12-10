export class AwUser {
    id: number;
    username: string;
    firstName: string;
    lastName: string;
    email: string;
    admin: boolean;
    constructor(usrn: string) {
        this.username = usrn;
    }
}