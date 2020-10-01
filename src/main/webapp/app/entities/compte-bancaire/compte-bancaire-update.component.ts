import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { ICompte_bancaire } from 'app/shared/model/compte-bancaire.model';
import { Compte_bancaireService } from './compte-bancaire.service';

@Component({
    selector: 'jhi-compte-bancaire-update',
    templateUrl: './compte-bancaire-update.component.html'
})
export class Compte_bancaireUpdateComponent implements OnInit {
    compte_bancaire: ICompte_bancaire;
    isSaving: boolean;

    constructor(protected compte_bancaireService: Compte_bancaireService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ compte_bancaire }) => {
            this.compte_bancaire = compte_bancaire;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.compte_bancaire.id !== undefined) {
            this.subscribeToSaveResponse(this.compte_bancaireService.update(this.compte_bancaire));
        } else {
            this.subscribeToSaveResponse(this.compte_bancaireService.create(this.compte_bancaire));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<ICompte_bancaire>>) {
        result.subscribe((res: HttpResponse<ICompte_bancaire>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }
}
