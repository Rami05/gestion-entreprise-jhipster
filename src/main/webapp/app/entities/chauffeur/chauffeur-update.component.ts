import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { IChauffeur } from 'app/shared/model/chauffeur.model';
import { ChauffeurService } from './chauffeur.service';
import { IVehicule } from 'app/shared/model/vehicule.model';
import { VehiculeService } from 'app/entities/vehicule';
import { IPermis_de_conduit } from 'app/shared/model/permis-de-conduit.model';
import { Permis_de_conduitService } from 'app/entities/permis-de-conduit';

@Component({
    selector: 'jhi-chauffeur-update',
    templateUrl: './chauffeur-update.component.html'
})
export class ChauffeurUpdateComponent implements OnInit {
    chauffeur: IChauffeur;
    isSaving: boolean;

    vehicules: IVehicule[];

    permis_de_conduits: IPermis_de_conduit[];

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected chauffeurService: ChauffeurService,
        protected vehiculeService: VehiculeService,
        protected permis_de_conduitService: Permis_de_conduitService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ chauffeur }) => {
            this.chauffeur = chauffeur;
        });
        this.vehiculeService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<IVehicule[]>) => mayBeOk.ok),
                map((response: HttpResponse<IVehicule[]>) => response.body)
            )
            .subscribe((res: IVehicule[]) => (this.vehicules = res), (res: HttpErrorResponse) => this.onError(res.message));
        this.permis_de_conduitService
            .query({ filter: 'chauffeur-is-null' })
            .pipe(
                filter((mayBeOk: HttpResponse<IPermis_de_conduit[]>) => mayBeOk.ok),
                map((response: HttpResponse<IPermis_de_conduit[]>) => response.body)
            )
            .subscribe(
                (res: IPermis_de_conduit[]) => {
                    if (!this.chauffeur.permis_de_conduitId) {
                        this.permis_de_conduits = res;
                    } else {
                        this.permis_de_conduitService
                            .find(this.chauffeur.permis_de_conduitId)
                            .pipe(
                                filter((subResMayBeOk: HttpResponse<IPermis_de_conduit>) => subResMayBeOk.ok),
                                map((subResponse: HttpResponse<IPermis_de_conduit>) => subResponse.body)
                            )
                            .subscribe(
                                (subRes: IPermis_de_conduit) => (this.permis_de_conduits = [subRes].concat(res)),
                                (subRes: HttpErrorResponse) => this.onError(subRes.message)
                            );
                    }
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.chauffeur.id !== undefined) {
            this.subscribeToSaveResponse(this.chauffeurService.update(this.chauffeur));
        } else {
            this.subscribeToSaveResponse(this.chauffeurService.create(this.chauffeur));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IChauffeur>>) {
        result.subscribe((res: HttpResponse<IChauffeur>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackVehiculeById(index: number, item: IVehicule) {
        return item.id;
    }

    trackPermis_de_conduitById(index: number, item: IPermis_de_conduit) {
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
