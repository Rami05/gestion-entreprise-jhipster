import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IVehicule } from 'app/shared/model/vehicule.model';
import { AccountService } from 'app/core';
import { VehiculeService } from './vehicule.service';

@Component({
    selector: 'jhi-vehicule',
    templateUrl: './vehicule.component.html'
})
export class VehiculeComponent implements OnInit, OnDestroy {
    vehicules: IVehicule[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        protected vehiculeService: VehiculeService,
        protected jhiAlertService: JhiAlertService,
        protected eventManager: JhiEventManager,
        protected accountService: AccountService
    ) {}

    loadAll() {
        this.vehiculeService
            .query()
            .pipe(
                filter((res: HttpResponse<IVehicule[]>) => res.ok),
                map((res: HttpResponse<IVehicule[]>) => res.body)
            )
            .subscribe(
                (res: IVehicule[]) => {
                    this.vehicules = res;
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
    }

    ngOnInit() {
        this.loadAll();
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInVehicules();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IVehicule) {
        return item.id;
    }

    registerChangeInVehicules() {
        this.eventSubscriber = this.eventManager.subscribe('vehiculeListModification', response => this.loadAll());
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
