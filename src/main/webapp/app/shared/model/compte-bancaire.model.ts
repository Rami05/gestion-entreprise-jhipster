export interface ICompte_bancaire {
    id?: number;
    rib?: number;
    agence?: string;
}

export class Compte_bancaire implements ICompte_bancaire {
    constructor(public id?: number, public rib?: number, public agence?: string) {}
}
