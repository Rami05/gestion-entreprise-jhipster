import { Component, OnInit, ElementRef } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';
import { IPermis_de_conduit } from 'app/shared/model/permis-de-conduit.model';
import { Permis_de_conduitService } from './permis-de-conduit.service';
import { IChauffeur } from 'app/shared/model/chauffeur.model';
import { ChauffeurService } from 'app/entities/chauffeur';

@Component({
    selector: 'jhi-permis-de-conduit-update',
    templateUrl: './permis-de-conduit-update.component.html'
})
export class Permis_de_conduitUpdateComponent implements OnInit {
    permis_de_conduit: IPermis_de_conduit;
    isSaving: boolean;

    chauffeurs: IChauffeur[];

    constructor(
        protected dataUtils: JhiDataUtils,
        protected jhiAlertService: JhiAlertService,
        protected permis_de_conduitService: Permis_de_conduitService,
        protected chauffeurService: ChauffeurService,
        protected elementRef: ElementRef,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ permis_de_conduit }) => {
            this.permis_de_conduit = permis_de_conduit;
        });
        this.chauffeurService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<IChauffeur[]>) => mayBeOk.ok),
                map((response: HttpResponse<IChauffeur[]>) => response.body)
            )
            .subscribe((res: IChauffeur[]) => (this.chauffeurs = res), (res: HttpErrorResponse) => this.onError(res.message));
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }

    setFileData(event, entity, field, isImage) {
        this.dataUtils.setFileData(event, entity, field, isImage);
    }

    clearInputImage(field: string, fieldContentType: string, idInput: string) {
        this.dataUtils.clearInputImage(this.permis_de_conduit, this.elementRef, field, fieldContentType, idInput);
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.permis_de_conduit.id !== undefined) {
            this.subscribeToSaveResponse(this.permis_de_conduitService.update(this.permis_de_conduit));
        } else {
            this.subscribeToSaveResponse(this.permis_de_conduitService.create(this.permis_de_conduit));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IPermis_de_conduit>>) {
        result.subscribe((res: HttpResponse<IPermis_de_conduit>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
}
