import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { ICOMMANDE_PRODUIT } from 'app/shared/model/commande-produit.model';
import { AccountService } from 'app/core';
import { COMMANDE_PRODUITService } from './commande-produit.service';

@Component({
    selector: 'jhi-commande-produit',
    templateUrl: './commande-produit.component.html'
})
export class COMMANDE_PRODUITComponent implements OnInit, OnDestroy {
    cOMMANDE_PRODUITS: ICOMMANDE_PRODUIT[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        protected cOMMANDE_PRODUITService: COMMANDE_PRODUITService,
        protected jhiAlertService: JhiAlertService,
        protected eventManager: JhiEventManager,
        protected accountService: AccountService
    ) {}

    loadAll() {
        this.cOMMANDE_PRODUITService
            .query()
            .pipe(
                filter((res: HttpResponse<ICOMMANDE_PRODUIT[]>) => res.ok),
                map((res: HttpResponse<ICOMMANDE_PRODUIT[]>) => res.body)
            )
            .subscribe(
                (res: ICOMMANDE_PRODUIT[]) => {
                    this.cOMMANDE_PRODUITS = res;
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
    }

    ngOnInit() {
        this.loadAll();
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInCOMMANDE_PRODUITS();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: ICOMMANDE_PRODUIT) {
        return item.id;
    }

    registerChangeInCOMMANDE_PRODUITS() {
        this.eventSubscriber = this.eventManager.subscribe('cOMMANDE_PRODUITListModification', response => this.loadAll());
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
