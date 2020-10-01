import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICOMMANDE_PRODUIT } from 'app/shared/model/commande-produit.model';

@Component({
    selector: 'jhi-commande-produit-detail',
    templateUrl: './commande-produit-detail.component.html'
})
export class COMMANDE_PRODUITDetailComponent implements OnInit {
    cOMMANDE_PRODUIT: ICOMMANDE_PRODUIT;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ cOMMANDE_PRODUIT }) => {
            this.cOMMANDE_PRODUIT = cOMMANDE_PRODUIT;
        });
    }

    previousState() {
        window.history.back();
    }
}
