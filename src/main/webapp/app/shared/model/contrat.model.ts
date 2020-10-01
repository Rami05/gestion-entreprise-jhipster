export interface IContrat {
    id?: number;
    reference?: string;
    employeNom?: string;
    employeId?: number;
}

export class Contrat implements IContrat {
    constructor(public id?: number, public reference?: string, public employeNom?: string, public employeId?: number) {}
}
