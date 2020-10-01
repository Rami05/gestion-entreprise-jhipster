export interface ICommande {
    id?: number;
    reference?: string;
}

export class Commande implements ICommande {
    constructor(public id?: number, public reference?: string) {}
}
