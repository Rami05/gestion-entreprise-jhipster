import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { ICommande } from 'app/shared/model/commande.model';
import { CommandeService } from './commande.service';

@Component({
    selector: 'jhi-commande-update',
    templateUrl: './commande-update.component.html'
})
export class CommandeUpdateComponent implements OnInit {
    commande: ICommande;
    isSaving: boolean;

    constructor(protected commandeService: CommandeService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ commande }) => {
            this.commande = commande;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.commande.id !== undefined) {
            this.subscribeToSaveResponse(this.commandeService.update(this.commande));
        } else {
            this.subscribeToSaveResponse(this.commandeService.create(this.commande));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<ICommande>>) {
        result.subscribe((res: HttpResponse<ICommande>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }
}
