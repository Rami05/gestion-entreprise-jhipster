import { IVehicule } from 'app/shared/model/vehicule.model';

export interface IChauffeur {
    id?: number;
    identite?: string;
    cin?: string;
    vehicules?: IVehicule[];
    permis_de_conduitNum_permis?: string;
    permis_de_conduitId?: number;
}

export class Chauffeur implements IChauffeur {
    constructor(
        public id?: number,
        public identite?: string,
        public cin?: string,
        public vehicules?: IVehicule[],
        public permis_de_conduitNum_permis?: string,
        public permis_de_conduitId?: number
    ) {}
}
