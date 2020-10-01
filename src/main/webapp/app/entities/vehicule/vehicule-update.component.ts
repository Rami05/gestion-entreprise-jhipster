import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { IVehicule } from 'app/shared/model/vehicule.model';
import { VehiculeService } from './vehicule.service';
import { IChauffeur } from 'app/shared/model/chauffeur.model';
import { ChauffeurService } from 'app/entities/chauffeur';

@Component({
    selector: 'jhi-vehicule-update',
    templateUrl: './vehicule-update.component.html'
})
export class VehiculeUpdateComponent implements OnInit {
    vehicule: IVehicule;
    isSaving: boolean;

    chauffeurs: IChauffeur[];

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected vehiculeService: VehiculeService,
        protected chauffeurService: ChauffeurService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ vehicule }) => {
            this.vehicule = vehicule;
        });
        this.chauffeurService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<IChauffeur[]>) => mayBeOk.ok),
                map((response: HttpResponse<IChauffeur[]>) => response.body)
            )
            .subscribe((res: IChauffeur[]) => (this.chauffeurs = res), (res: HttpErrorResponse) => this.onError(res.message));
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.vehicule.id !== undefined) {
            this.subscribeToSaveResponse(this.vehiculeService.update(this.vehicule));
        } else {
            this.subscribeToSaveResponse(this.vehiculeService.create(this.vehicule));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IVehicule>>) {
        result.subscribe((res: HttpResponse<IVehicule>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackChauffeurById(index: number, item: IChauffeur) {
        return item.id;
    }

    getSelected(selectedVals: Array<any>, option: any) {
        if (selectedVals) {
            for (let i = 0; i < selectedVals.length; i++) {
                if (option.id === selectedVals[i].id) {
                    return selectedVals[i];
                }
            }
        }
        return option;
    }
}
