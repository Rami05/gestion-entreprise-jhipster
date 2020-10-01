import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { ICOMMANDE_PRODUIT } from 'app/shared/model/commande-produit.model';
import { COMMANDE_PRODUITService } from './commande-produit.service';

@Component({
    selector: 'jhi-commande-produit-update',
    templateUrl: './commande-produit-update.component.html'
})
export class COMMANDE_PRODUITUpdateComponent implements OnInit {
    cOMMANDE_PRODUIT: ICOMMANDE_PRODUIT;
    isSaving: boolean;
    dateCreationDp: any;
    dateModification: string;

    constructor(protected cOMMANDE_PRODUITService: COMMANDE_PRODUITService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ cOMMANDE_PRODUIT }) => {
            this.cOMMANDE_PRODUIT = cOMMANDE_PRODUIT;
            this.dateModification =
                this.cOMMANDE_PRODUIT.dateModification != null ? this.cOMMANDE_PRODUIT.dateModification.format(DATE_TIME_FORMAT) : null;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.cOMMANDE_PRODUIT.dateModification = this.dateModification != null ? moment(this.dateModification, DATE_TIME_FORMAT) : null;
        if (this.cOMMANDE_PRODUIT.id !== undefined) {
            this.subscribeToSaveResponse(this.cOMMANDE_PRODUITService.update(this.cOMMANDE_PRODUIT));
        } else {
            this.subscribeToSaveResponse(this.cOMMANDE_PRODUITService.create(this.cOMMANDE_PRODUIT));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<ICOMMANDE_PRODUIT>>) {
        result.subscribe((res: HttpResponse<ICOMMANDE_PRODUIT>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }
}
