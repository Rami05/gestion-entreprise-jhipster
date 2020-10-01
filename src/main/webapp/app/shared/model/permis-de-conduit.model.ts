export interface IPermis_de_conduit {
    id?: number;
    num_permis?: string;
    copie_permisContentType?: string;
    copie_permis?: any;
    chauffeurId?: number;
}

export class Permis_de_conduit implements IPermis_de_conduit {
    constructor(
        public id?: number,
        public num_permis?: string,
        public copie_permisContentType?: string,
        public copie_permis?: any,
        public chauffeurId?: number
    ) {}
}
