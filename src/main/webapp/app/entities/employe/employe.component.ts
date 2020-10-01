import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IEmploye } from 'app/shared/model/employe.model';
import { AccountService } from 'app/core';
import { EmployeService } from './employe.service';

@Component({
    selector: 'jhi-employe',
    templateUrl: './employe.component.html'
})
export class EmployeComponent implements OnInit, OnDestroy {
    employes: IEmploye[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        protected employeService: EmployeService,
        protected jhiAlertService: JhiAlertService,
        protected eventManager: JhiEventManager,
        protected accountService: AccountService
    ) {}

    loadAll() {
        this.employeService
            .query()
            .pipe(
                filter((res: HttpResponse<IEmploye[]>) => res.ok),
                map((res: HttpResponse<IEmploye[]>) => res.body)
            )
            .subscribe(
                (res: IEmploye[]) => {
                    this.employes = res;
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
    }

    ngOnInit() {
        this.loadAll();
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInEmployes();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IEmploye) {
        return item.id;
    }

    registerChangeInEmployes() {
        this.eventSubscriber = this.eventManager.subscribe('employeListModification', response => this.loadAll());
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
