import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { IProduit } from 'app/shared/model/produit.model';
import { ProduitService } from './produit.service';

@Component({
    selector: 'jhi-produit-update',
    templateUrl: './produit-update.component.html'
})
export class ProduitUpdateComponent implements OnInit {
    produit: IProduit;
    isSaving: boolean;

    constructor(protected produitService: ProduitService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ produit }) => {
            this.produit = produit;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.produit.id !== undefined) {
            this.subscribeToSaveResponse(this.produitService.update(this.produit));
        } else {
            this.subscribeToSaveResponse(this.produitService.create(this.produit));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IProduit>>) {
        result.subscribe((res: HttpResponse<IProduit>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }
}
