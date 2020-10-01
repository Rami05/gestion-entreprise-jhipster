export interface IEntreprise {
    id?: number;
    raison_social?: string;
    chiffre_affaire?: number;
    gerant?: string;
    secteur?: string;
    siege?: string;
}

export class Entreprise implements IEntreprise {
    constructor(
        public id?: number,
        public raison_social?: string,
        public chiffre_affaire?: number,
        public gerant?: string,
        public secteur?: string,
        public siege?: string
    ) {}
}
