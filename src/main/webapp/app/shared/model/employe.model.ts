export interface IEmploye {
    id?: number;
    nom?: string;
    prenom?: string;
}

export class Employe implements IEmploye {
    constructor(public id?: number, public nom?: string, public prenom?: string) {}
}
