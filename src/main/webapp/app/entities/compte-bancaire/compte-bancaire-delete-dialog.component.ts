import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ICompte_bancaire } from 'app/shared/model/compte-bancaire.model';
import { Compte_bancaireService } from './compte-bancaire.service';

@Component({
    selector: 'jhi-compte-bancaire-delete-dialog',
    templateUrl: './compte-bancaire-delete-dialog.component.html'
})
export class Compte_bancaireDeleteDialogComponent {
    compte_bancaire: ICompte_bancaire;

    constructor(
        protected compte_bancaireService: Compte_bancaireService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.compte_bancaireService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'compte_bancaireListModification',
                content: 'Deleted an compte_bancaire'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-compte-bancaire-delete-popup',
    template: ''
})
export class Compte_bancaireDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ compte_bancaire }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(Compte_bancaireDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.compte_bancaire = compte_bancaire;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/compte-bancaire', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/compte-bancaire', { outlets: { popup: null } }]);
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
