import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { IEmploye } from 'app/shared/model/employe.model';
import { EmployeService } from './employe.service';

@Component({
    selector: 'jhi-employe-update',
    templateUrl: './employe-update.component.html'
})
export class EmployeUpdateComponent implements OnInit {
    employe: IEmploye;
    isSaving: boolean;

    constructor(protected employeService: EmployeService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ employe }) => {
            this.employe = employe;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.employe.id !== undefined) {
            this.subscribeToSaveResponse(this.employeService.update(this.employe));
        } else {
            this.subscribeToSaveResponse(this.employeService.create(this.employe));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IEmploye>>) {
        result.subscribe((res: HttpResponse<IEmploye>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }
}
