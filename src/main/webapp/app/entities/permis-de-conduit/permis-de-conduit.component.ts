import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { IPermis_de_conduit } from 'app/shared/model/permis-de-conduit.model';
import { AccountService } from 'app/core';
import { Permis_de_conduitService } from './permis-de-conduit.service';

@Component({
    selector: 'jhi-permis-de-conduit',
    templateUrl: './permis-de-conduit.component.html'
})
export class Permis_de_conduitComponent implements OnInit, OnDestroy {
    permis_de_conduits: IPermis_de_conduit[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        protected permis_de_conduitService: Permis_de_conduitService,
        protected jhiAlertService: JhiAlertService,
        protected dataUtils: JhiDataUtils,
        protected eventManager: JhiEventManager,
        protected accountService: AccountService
    ) {}

    loadAll() {
        this.permis_de_conduitService
            .query()
            .pipe(
                filter((res: HttpResponse<IPermis_de_conduit[]>) => res.ok),
                map((res: HttpResponse<IPermis_de_conduit[]>) => res.body)
            )
            .subscribe(
                (res: IPermis_de_conduit[]) => {
                    this.permis_de_conduits = res;
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
    }

    ngOnInit() {
        this.loadAll();
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInPermis_de_conduits();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IPermis_de_conduit) {
        return item.id;
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }

    registerChangeInPermis_de_conduits() {
        this.eventSubscriber = this.eventManager.subscribe('permis_de_conduitListModification', response => this.loadAll());
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
