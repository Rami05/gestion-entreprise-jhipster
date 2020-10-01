export interface IProduit {
    id?: number;
    libelle?: string;
}

export class Produit implements IProduit {
    constructor(public id?: number, public libelle?: string) {}
}
