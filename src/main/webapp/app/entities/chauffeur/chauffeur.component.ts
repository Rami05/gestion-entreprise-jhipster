import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IChauffeur } from 'app/shared/model/chauffeur.model';
import { AccountService } from 'app/core';
import { ChauffeurService } from './chauffeur.service';

@Component({
    selector: 'jhi-chauffeur',
    templateUrl: './chauffeur.component.html'
})
export class ChauffeurComponent implements OnInit, OnDestroy {
    chauffeurs: IChauffeur[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        protected chauffeurService: ChauffeurService,
        protected jhiAlertService: JhiAlertService,
        protected eventManager: JhiEventManager,
        protected accountService: AccountService
    ) {}

    loadAll() {
        this.chauffeurService
            .query()
            .pipe(
                filter((res: HttpResponse<IChauffeur[]>) => res.ok),
                map((res: HttpResponse<IChauffeur[]>) => res.body)
            )
            .subscribe(
                (res: IChauffeur[]) => {
                    this.chauffeurs = res;
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
    }

    ngOnInit() {
        this.loadAll();
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInChauffeurs();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IChauffeur) {
        return item.id;
    }

    registerChangeInChauffeurs() {
        this.eventSubscriber = this.eventManager.subscribe('chauffeurListModification', response => this.loadAll());
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
