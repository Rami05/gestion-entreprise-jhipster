import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { ICompte_bancaire } from 'app/shared/model/compte-bancaire.model';
import { AccountService } from 'app/core';
import { Compte_bancaireService } from './compte-bancaire.service';

@Component({
    selector: 'jhi-compte-bancaire',
    templateUrl: './compte-bancaire.component.html'
})
export class Compte_bancaireComponent implements OnInit, OnDestroy {
    compte_bancaires: ICompte_bancaire[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        protected compte_bancaireService: Compte_bancaireService,
        protected jhiAlertService: JhiAlertService,
        protected eventManager: JhiEventManager,
        protected accountService: AccountService
    ) {}

    loadAll() {
        this.compte_bancaireService
            .query()
            .pipe(
                filter((res: HttpResponse<ICompte_bancaire[]>) => res.ok),
                map((res: HttpResponse<ICompte_bancaire[]>) => res.body)
            )
            .subscribe(
                (res: ICompte_bancaire[]) => {
                    this.compte_bancaires = res;
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
    }

    ngOnInit() {
        this.loadAll();
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInCompte_bancaires();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: ICompte_bancaire) {
        return item.id;
    }

    registerChangeInCompte_bancaires() {
        this.eventSubscriber = this.eventManager.subscribe('compte_bancaireListModification', response => this.loadAll());
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
