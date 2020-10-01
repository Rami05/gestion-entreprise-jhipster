import { Moment } from 'moment';

export const enum Etat {
    EN_COURS = 'EN_COURS',
    LIVRER = 'LIVRER',
    ANNULER = 'ANNULER'
}

export interface ICOMMANDE_PRODUIT {
    id?: number;
    dateCreation?: Moment;
    dateModification?: Moment;
    etat?: Etat;
}

export class COMMANDE_PRODUIT implements ICOMMANDE_PRODUIT {
    constructor(public id?: number, public dateCreation?: Moment, public dateModification?: Moment, public etat?: Etat) {}
}
