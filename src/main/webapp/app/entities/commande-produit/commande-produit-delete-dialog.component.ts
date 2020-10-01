import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ICOMMANDE_PRODUIT } from 'app/shared/model/commande-produit.model';
import { COMMANDE_PRODUITService } from './commande-produit.service';

@Component({
    selector: 'jhi-commande-produit-delete-dialog',
    templateUrl: './commande-produit-delete-dialog.component.html'
})
export class COMMANDE_PRODUITDeleteDialogComponent {
    cOMMANDE_PRODUIT: ICOMMANDE_PRODUIT;

    constructor(
        protected cOMMANDE_PRODUITService: COMMANDE_PRODUITService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.cOMMANDE_PRODUITService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'cOMMANDE_PRODUITListModification',
                content: 'Deleted an cOMMANDE_PRODUIT'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-commande-produit-delete-popup',
    template: ''
})
export class COMMANDE_PRODUITDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ cOMMANDE_PRODUIT }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(COMMANDE_PRODUITDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.cOMMANDE_PRODUIT = cOMMANDE_PRODUIT;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/commande-produit', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/commande-produit', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    }
                );
            }, 0);
        });
    }

    ngOnDestroy() {
        this.ngbModalRef = null;
    }
}
