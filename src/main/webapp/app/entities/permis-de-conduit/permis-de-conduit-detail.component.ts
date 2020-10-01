import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IPermis_de_conduit } from 'app/shared/model/permis-de-conduit.model';

@Component({
    selector: 'jhi-permis-de-conduit-detail',
    templateUrl: './permis-de-conduit-detail.component.html'
})
export class Permis_de_conduitDetailComponent implements OnInit {
    permis_de_conduit: IPermis_de_conduit;

    constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ permis_de_conduit }) => {
            this.permis_de_conduit = permis_de_conduit;
        });
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }
    previousState() {
        window.history.back();
    }
}
