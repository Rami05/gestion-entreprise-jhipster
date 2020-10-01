import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { IContrat } from 'app/shared/model/contrat.model';
import { ContratService } from './contrat.service';
import { IEmploye } from 'app/shared/model/employe.model';
import { EmployeService } from 'app/entities/employe';

@Component({
    selector: 'jhi-contrat-update',
    templateUrl: './contrat-update.component.html'
})
export class ContratUpdateComponent implements OnInit {
    contrat: IContrat;
    isSaving: boolean;

    employes: IEmploye[];

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected contratService: ContratService,
        protected employeService: EmployeService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ contrat }) => {
            this.contrat = contrat;
        });
        this.employeService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<IEmploye[]>) => mayBeOk.ok),
                map((response: HttpResponse<IEmploye[]>) => response.body)
            )
            .subscribe((res: IEmploye[]) => (this.employes = res), (res: HttpErrorResponse) => this.onError(res.message));
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.contrat.id !== undefined) {
            this.subscribeToSaveResponse(this.contratService.update(this.contrat));
        } else {
            this.subscribeToSaveResponse(this.contratService.create(this.contrat));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IContrat>>) {
        result.subscribe((res: HttpResponse<IContrat>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackEmployeById(index: number, item: IEmploye) {
        return item.id;
    }
}
