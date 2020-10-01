import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IChauffeur } from 'app/shared/model/chauffeur.model';

@Component({
    selector: 'jhi-chauffeur-detail',
    templateUrl: './chauffeur-detail.component.html'
})
export class ChauffeurDetailComponent implements OnInit {
    chauffeur: IChauffeur;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ chauffeur }) => {
            this.chauffeur = chauffeur;
        });
    }

    previousState() {
        window.history.back();
    }
}
