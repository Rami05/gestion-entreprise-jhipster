import { IChauffeur } from 'app/shared/model/chauffeur.model';

export interface IVehicule {
    id?: number;
    identifiant?: string;
    chauffeurs?: IChauffeur[];
}

export class Vehicule implements IVehicule {
    constructor(public id?: number, public identifiant?: string, public chauffeurs?: IChauffeur[]) {}
}
