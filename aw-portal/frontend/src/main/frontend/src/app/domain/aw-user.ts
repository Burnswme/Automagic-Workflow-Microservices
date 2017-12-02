export class AwUser {
    id: number;
    email: string;
    firstName: string;
    lastName: string;
    username: string;
    password: string;
    admin: boolean;
    constructor(usrn: string, pswd: string) { 
        this.username = usrn;
        this.password = pswd;
    }
}