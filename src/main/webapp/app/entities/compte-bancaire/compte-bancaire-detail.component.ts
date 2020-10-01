import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICompte_bancaire } from 'app/shared/model/compte-bancaire.model';

@Component({
    selector: 'jhi-compte-bancaire-detail',
    templateUrl: './compte-bancaire-detail.component.html'
})
export class Compte_bancaireDetailComponent implements OnInit {
    compte_bancaire: ICompte_bancaire;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ compte_bancaire }) => {
            this.compte_bancaire = compte_bancaire;
        });
    }

    previousState() {
        window.history.back();
    }
}
